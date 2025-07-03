import React, { useState, useEffect, useRef } from 'react';
import remove from '../Images/Delete.svg';
import axios from 'axios';
import FileUpload from '../Images/file.png';
const Tenant = () => {
  const [fullAgreementData, setFullAgreementData] = useState([]);
  const [message, setMessage] = useState('');
  const [tenantList, setTenantList] = useState([]);
  const [selectedPdf, setSelectedPdf] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedAgreementFile, setSelectedAgreementFile] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [selectedId, setSelectedId] = useState(null);
  const [selectedTenantName, setSelectedTenantName] = useState("");
  const [selectedPropertyName, setSelectedPropertyName] = useState("");
  const [selectedDoorNo, setSelectedDoorNo] = useState("");
  console.log(message);
  const scrollRef = useRef(null);
  const isDragging = useRef(false);
  const start = useRef({ x: 0, y: 0 });
  const scroll = useRef({ left: 0, top: 0 });
  const velocity = useRef({ x: 0, y: 0 });
  const animationFrame = useRef(null);
  const lastMove = useRef({ time: 0, x: 0, y: 0 });
  const handleMouseDown = (e) => {
    isDragging.current = true;
    start.current = { x: e.clientX, y: e.clientY };
    scroll.current = {
      left: scrollRef.current.scrollLeft,
      top: scrollRef.current.scrollTop,
    };
    lastMove.current = {
      time: Date.now(),
      x: e.clientX,
      y: e.clientY,
    };
    scrollRef.current.style.cursor = 'grabbing';
    scrollRef.current.style.userSelect = 'none';
    cancelMomentum();
  };
  const handleMouseMove = (e) => {
    if (!isDragging.current) return;

    const dx = e.clientX - start.current.x;
    const dy = e.clientY - start.current.y;

    const now = Date.now();
    const dt = now - lastMove.current.time || 16;

    velocity.current = {
      x: (e.clientX - lastMove.current.x) / dt,
      y: (e.clientY - lastMove.current.y) / dt,
    };

    scrollRef.current.scrollLeft = scroll.current.left - dx;
    scrollRef.current.scrollTop = scroll.current.top - dy;

    lastMove.current = {
      time: now,
      x: e.clientX,
      y: e.clientY,
    };
  };

  const handleMouseUp = () => {
    if (!isDragging.current) return;
    isDragging.current = false;
    scrollRef.current.style.cursor = '';
    scrollRef.current.style.userSelect = '';
    applyMomentum();
  };

  const cancelMomentum = () => {
    if (animationFrame.current) {
      cancelAnimationFrame(animationFrame.current);
      animationFrame.current = null;
    }
  };
  const applyMomentum = () => {
    const friction = 0.95;
    const minVelocity = 0.1;

    const step = () => {
      const { x, y } = velocity.current;
      if (Math.abs(x) > minVelocity || Math.abs(y) > minVelocity) {
        scrollRef.current.scrollLeft -= x * 20;
        scrollRef.current.scrollTop -= y * 20;
        velocity.current.x *= friction;
        velocity.current.y *= friction;
        animationFrame.current = requestAnimationFrame(step);
      } else {
        cancelMomentum();
      }
    };

    animationFrame.current = requestAnimationFrame(step);
  };
  useEffect(() => {
    fetchAgreements();
  }, []);
  const fetchAgreements = async () => {
    try {
      const response = await fetch('https://backendaab.in/aabuildersDash/api/agreements/all');
      if (response.ok) {
        const data = await response.json();
        setFullAgreementData(data);
      } else {
        setMessage('Error fetching agreements.');
      }
    } catch (error) {
      console.error('Error:', error);
      setMessage('Error fetching agreements.');
    }
  };
  useEffect(() => {
    const fetchTenants = async () => {
      try {
        const response = await axios.get('https://backendaab.in/aabuildersDash/api/tenant-groups/all');
        const updatedTenants = response.data.map((tenant) => {
          if (tenant.aadhaarFile) {
            return {
              ...tenant,
              aadhaarImageUrl: `data:image/jpeg;base64,${tenant.aadhaarFile}`,
            };
          }
          return tenant;
        });
        setTenantList(updatedTenants);
      } catch (error) {
        console.error('Error fetching tenants:', error);
      }
    };
    fetchTenants();
  }, []);
  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setSelectedAgreementFile(file);
    }
    // This ensures the input is cleared even if the same file is selected again next time
    e.target.value = '';
  };
  const handleTenantClick = (aadhaarFile) => {
    const blob = new Blob([Uint8Array.from(atob(aadhaarFile), c => c.charCodeAt(0))], { type: 'application/pdf' });
    const pdfUrl = URL.createObjectURL(blob);
    setSelectedPdf(pdfUrl);
    setIsModalOpen(true);
  };
  const closeModal = () => {
    setIsModalOpen(false);
    setSelectedPdf(null);
  };
  const openUploadModal = (id, propertyName, tenantName, doorNo) => {
    setSelectedId(id);
    setSelectedDoorNo(doorNo);
    setSelectedPropertyName(propertyName);
    setSelectedTenantName(tenantName);
    setShowModal(true);
  };
  const closeModals = () => {
    setShowModal(false);
    setSelectedAgreementFile(null);
    setSelectedDoorNo('');
    setSelectedPropertyName('');
    setSelectedTenantName('');
  };
  const handleUpdate = async () => {
    let confirmedAgreementUrl = "";
    const filename = `${selectedPropertyName}_${selectedDoorNo}_${selectedTenantName}`;
    const formData = new FormData();
    formData.append("pdf", selectedAgreementFile);
    formData.append("filename", filename);
    const uploadResponse = await fetch("https://backendaab.in/aabuilderDash/agreement/googleUploader/uploadToGoogleDrive", {
      method: "POST",
      body: formData,
    });
    if (!uploadResponse.ok) throw new Error("PDF upload failed");
    const uploadResult = await uploadResponse.json();
    confirmedAgreementUrl = uploadResult.url;
    try {
      const res = await fetch(`https://backendaab.in/aabuildersDash/api/agreements/updateConfirmedUrl/${selectedId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ confirmedAgreementUrl: confirmedAgreementUrl }),
      });
      if (!res.ok) {
        throw new Error("Failed to update URL");
      }
      window.location.reload();
    } catch (err) {
      console.error(err.message);
    }
  };
  return (
    <div className="lg:p-6 lg:w-[1724px] w-full bg-white lg:ml-12">
      <div className=" p-4">
        <div className="flex justify-between items-center mb-4">
          <button className="text-xl">

          </button>
          <div className="relative">
            <input
              type="text"
              placeholder="Search"
              className="pl-4 pr-10 py-2 border rounded-full shadow-md focus:outline-none"
            />
            <span className="absolute right-3 top-2.5 text-gray-500"></span>
          </div>
        </div>
        <div
          ref={scrollRef}
          className="w-full rounded-lg border border-gray-200 border-l-8 border-l-[#BF9853] h-[760px] overflow-x-auto select-none no-scrollbar"
          onMouseDown={handleMouseDown}
          onMouseMove={handleMouseMove}
          onMouseUp={handleMouseUp}
          onMouseLeave={handleMouseUp}
        >
          <table className="w-full table-auto ">
            <thead>
              <tr className="bg-[#FAF6ED] text-left">
                <th className="px-4 py-2 font-bold">S.No</th>
                <th className="px-4 py-2 font-bold">Shop No</th>
                <th className="px-4 py-2 font-bold">Shop Name</th>
                <th className="px-4 py-2 font-bold">Door No</th>
                <th className="px-4 py-2 font-bold">Tenant Name</th>
                <th className="px-4 py-2 font-bold">Advance</th>
                <th className="px-4 py-2 font-bold">Rent</th>
                <th className="px-4 py-2 font-bold">Agreement</th>
                <th className="px-4 py-2 font-bold">Aadhaar File</th>
                <th className="px-4 py-2 font-bold">Delete</th>
                <th className="px-4 py-2 font-bold">Upload</th>
              </tr>
            </thead>
            <tbody>
              {fullAgreementData.map((agreement, index) => {
                const tenantName = agreement.agreementTenantNames?.[0]?.tenantName || '—';
                const matchedGroup = tenantList.find(group => group.tenantName === tenantName);
                const aadhaarFile = matchedGroup?.tenantDetailsList?.[0]?.aadhaarFile;
                const shopNos = agreement.propertyTypeDetails.map((p) => p.shopNos);
                const doorNos = agreement.propertyTypeDetails.map((p) => p.doorNo);
                const totalAdvance = agreement.propertyTypeDetails.reduce((sum, p) => sum + parseFloat(p.advance || 0), 0);
                const totalRent = agreement.propertyTypeDetails.reduce((sum, p) => sum + parseFloat(p.rent || 0), 0);

                return (
                  <tr key={agreement.id} className="odd:bg-white even:bg-[#FAF6ED]">
                    <td className="py-2 px-4 text-sm font-semibold text-left">{index + 1}</td>
                    <td className="py-2 px-4 text-sm font-semibold text-left">[{shopNos.join(', ')}]</td>
                    <td className="py-2 px-4 text-sm font-semibold text-left">{agreement.propertyName}</td>
                    <td className="py-2 px-4 text-sm font-semibold text-left">[{doorNos.join(', ')}]</td>
                    <td className="py-2 px-4 text-sm font-semibold text-left">{tenantName}</td>
                    <td className="py-2 px-4 text-sm font-semibold text-left">₹{totalAdvance.toLocaleString()}</td>
                    <td className="py-2 px-4 text-sm font-semibold text-left">₹{totalRent.toLocaleString()}</td>
                    <td className="py-2 pr-4 text-center">
                      <a
                        href={agreement.agreementUrl}
                        className="text-red-500 underline font-semibold "
                        target="_blank"
                        rel="noopener noreferrer"
                      >
                        View
                      </a>
                    </td>
                    <td className="py-2 pr-2 text-center">
                      {aadhaarFile ? (
                        <button
                          onClick={() => handleTenantClick(aadhaarFile)}
                          className="text-blue-500 underline text-sm font-medium hover:text-blue-700"
                        >
                          View
                        </button>
                      ) : (
                        <span className="text-gray-400 text-sm">N/A</span>
                      )}
                    </td>
                    <td className="py-2 px-4">
                      <button>
                        <img
                          src={remove}
                          alt="delete"
                          className="w-4 h-4 transform hover:scale-110 hover:brightness-110 transition duration-200"
                        />
                      </button>
                    </td>
                    <td className="py-2 px-4 text-center flex gap-4">
                      <button onClick={() => openUploadModal(agreement.id, agreement.propertyName, tenantName, doorNos)}>
                        <img
                          src={FileUpload}
                          alt="upload"
                          className="w-4 h-4 transform hover:scale-110 hover:brightness-110 transition duration-200"
                        />
                      </button>

                      {agreement.confirmedAgreementUrl ? (
                        <a
                          href={agreement.confirmedAgreementUrl}
                          className="text-red-500 underline font-semibold"
                          target="_blank"
                          rel="noopener noreferrer"
                        >
                          View
                        </a>
                      ) : (
                        <span className="text-gray-400">—</span>
                      )}
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
          {/* Modal for PDF */}
          {isModalOpen && selectedPdf && (
            <div style={{
              position: 'fixed',
              top: 0,
              left: 0,
              width: '100%',
              height: '100%',
              backgroundColor: 'rgba(0,0,0,0.5)',
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              zIndex: 1000,
            }}>
              <div style={{
                backgroundColor: '#fff',
                padding: '20px',
                borderRadius: '10px',
                width: '80%',
                height: '80%',
                position: 'relative'
              }}>
                <button onClick={closeModal} style={{
                  position: 'absolute',
                  top: 10,
                  right: 10,
                  fontSize: '18px'
                }}>X</button>

                <iframe
                  src={selectedPdf}
                  title="Aadhaar PDF"
                  width="100%"
                  height="100%"
                ></iframe>
              </div>
            </div>
          )}
          {showModal && (
            <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-40">
              <div className="bg-white p-6 rounded-lg shadow-md w-[550px]">

                <h2 className="text-lg font-semibold mb-4">Upload Confirmed Agreement File</h2>
                <div className='text-left p-6'>
                  <label className="cursor-pointer text-red-500 pl-6">
                    Choose file
                    <input
                      type="file"
                      onChange={handleFileChange}
                      className="hidden"
                    />
                  </label>
                  {selectedAgreementFile && <span className="text-gray-500 pl-3">{selectedAgreementFile.name}</span>}
                </div>
                <div className="flex justify-end space-x-2">
                  <button
                    onClick={handleUpdate}
                    className="bg-[#BF9853] text-white px-4 py-1 rounded hover:bg-[#BF9853]"
                  >
                    Submit
                  </button>
                  <button
                    onClick={closeModals}
                    className=" text-black px-4 py-1 rounded hover:bg-gray-50"
                  >
                    Cancel
                  </button>
                </div>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};
export default Tenant